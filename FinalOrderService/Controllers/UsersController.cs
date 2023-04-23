using Microsoft.AspNetCore.Mvc;

[ApiController]
[Route("[controller]")]
public class UsersController : ControllerBase
{
    private readonly ILogger<UsersController> _logger;
    private readonly IUserRepository _userRepository;

    public UsersController(ILogger<UsersController> logger, IUserRepository userRepository)
    {
        _logger = logger;
        _userRepository = userRepository;
    }


    [HttpPost]
    public async Task<IActionResult> Create(User user)
    {
        //Guid userGuid = new Guid("E8E369C0-960B-4584-9A81-F9FF9F98DBD6");
        try
        {
            User uu = await _userRepository.GetByUserEmail(user.Email);
            //Console.WriteLine(uu);

            if (uu != null)
            {
                return Ok(new
                {
                    Success = true,
                    Message = "User already exists!",
                    UserGuid = uu.UserGuid,
                    Email = uu.Email
                });

            }

            var userGuid = await _userRepository.Create(user);

            return Ok(new
            {
                Success = true,
                Message = "User created.",
                UserGuid = userGuid
            });

        }
        catch (Exception e)
        {
            return Ok("something happened");
        }
    }

    [HttpGet]
    public async Task<IActionResult> GetAll()
    {
        try
        {
            var Data = await _userRepository.GetAll();
            return Ok(new
            {
                Success = true,
                Message = "all users returned.",
                Data
            });
        }
        catch (Exception ex)
        {
            Console.WriteLine(ex.Message);
            return StatusCode(500, ex.Message);
        }
    }

    [HttpGet]
    [Route("{userGuid}")]
    public async Task<IActionResult> GetById(Guid userGuid)
    {
        try
        {
            var Data = await _userRepository.GetByUserGuid(userGuid);
            return Ok(new
            {
                Success = true,
                Message = "User fetched.",
                Data
            });
        }
        catch (Exception ex)
        {
            Console.WriteLine(ex.Message);
            return StatusCode(500, ex.Message);
        }
    }
}