using Dapper;
using System.Data;


public class OrderRepository : IOrderRepository
{
    private readonly DapperContext _context;
    public OrderRepository(DapperContext context)
    {
        _context = context;
    }

    public async Task<Guid> Create(OrderDTOCreate orderDTOCreate, Guid userGuid)
    {
        string sqlQueryOrders = "INSERT INTO Orders (OrderGuid, UserGuid, BasketGuid, CreatedDate) VALUES (@OrderGuid, @UserGuid, @BasketGuid, @CreatedDate)";
        var parametersOrders = new DynamicParameters();

        Guid orderGuid = Guid.NewGuid();
        //Guid userGuid = Guid.NewGuid(); //TODO

        parametersOrders.Add("OrderGuid", orderGuid, DbType.Guid);
        parametersOrders.Add("UserGuid", userGuid, DbType.Guid);
        parametersOrders.Add("BasketGuid", orderDTOCreate.BasketGuid, DbType.Guid);
        parametersOrders.Add("CreatedDate", DateTime.UtcNow, DbType.DateTime);

        using (var connection = _context.CreateConnection())
        {
            await connection.ExecuteAsync(sqlQueryOrders, parametersOrders);
        }

        if (orderDTOCreate.ReadBasket == true)
        {
            Console.WriteLine("read basket");

            var client = new HttpClient();
            var basket = await client.GetFromJsonAsync<Basket>($"http://localhost:8082/basket/{orderDTOCreate.BasketGuid}");
            //var basket = await client.GetStringAsync($"http://localhost:8082/basket/{orderDTOCreate.BasketGuid}");
            //Console.WriteLine(basket);


            if (basket != null && basket.Products != null)
            {
                Console.WriteLine(basket);
                orderDTOCreate.Products = basket.Products;

                foreach (ProductDTOCreate product in basket.Products)
                {
                    //Console.WriteLine(product.ProductGuid);
                }
            }

        }


        string sqlQueryProducts = "INSERT INTO Products (ProductGuid, OrderGuid, Title, Description, PublishedDate, CreatedDate) VALUES (@ProductGuid, @OrderGuid, @Title, @Description, @PublishedDate, @CreatedDate)";

        foreach (ProductDTOCreate product in orderDTOCreate.Products)
        {

            //Console.WriteLine(product.ProductGuid);
            //Console.WriteLine(product.PublishedDate);

            var parametersProducts = new DynamicParameters();
            parametersProducts.Add("ProductGuid", product.ProductGuid, DbType.Guid);
            parametersProducts.Add("OrderGuid", orderGuid, DbType.Guid);
            parametersProducts.Add("Name", product.Name, DbType.String);
            parametersProducts.Add("Price", product.Price, DbType.Double);
            parametersProducts.Add("Description", product.Description, DbType.String);
            parametersProducts.Add("PublishedDate", product.PublishedDate, DbType.DateTime);

            // even if they pass in the created date from the ProductService or BasketService, I'm setting a new one here, since it represents the creation of this record
            parametersProducts.Add("CreatedDate", /*product.CreatedDate == null ? */ DateTime.UtcNow /*: product.CreatedDate*/, DbType.DateTime);

            using (var connection = _context.CreateConnection())
            {
                await connection.ExecuteAsync(sqlQueryProducts, parametersProducts);
            }
        }

        return orderGuid;
    }


    public IEnumerable<Order> GetAllWithProducts()
    {
        string sql = "SELECT * FROM Orders AS O INNER JOIN Products AS B ON O.OrderGuid = B.OrderGuid;";

        using (var connection = _context.CreateConnection())
        {
            var orderDictionary = new Dictionary<Guid, Order>();

            var ordersList = connection.Query<Order, Product, Order>(
                sql,
                (order, orderProduct) =>
                {
                    Order orderEntry;

                    if (!orderDictionary.TryGetValue(order.OrderGuid, out orderEntry))
                    {
                        orderEntry = order;
                        orderEntry.Products = orderEntry.Products ?? new List<Product>();
                        orderDictionary.Add(orderEntry.OrderGuid, orderEntry);
                    }

                    orderEntry.Products.Add(orderProduct);
                    return orderEntry;
                },
                splitOn: "ProductGuid")
            .Distinct()
            .ToList();

            Console.WriteLine("Orders Count:" + ordersList.Count);

            foreach (Order order in ordersList)
            {
                //Console.WriteLine("orderID:" + order.OrderGuid);
                //Console.WriteLine(string.Join(",", order.Products.AsList<Product>()));
            }

            return ordersList;

        }
    }
}