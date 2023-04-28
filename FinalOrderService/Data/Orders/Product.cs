using System.ComponentModel.DataAnnotations;

public class Product
{
    [Key]
    public Guid ProductGuid { get; set; }

    [Required]
    public Guid OrderGuid { get; set; }

    [Required]
    public String ? Name { get; set; }

    [Required]
    public String ? Description { get; set; }

    [Required]
    public String  ? Price { get; set; }

    [Required]
    public DateTime PublishedDate { get; set; }

    [Required]
    public DateTime CreatedDate { get; set; }

}