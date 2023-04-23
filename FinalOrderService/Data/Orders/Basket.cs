using System.ComponentModel.DataAnnotations;

public class Basket
{
    [Key]
    public Guid basketId { get; set; }

    [Required]
    public List<ProductDTOCreate>? Products { get; set; }
}