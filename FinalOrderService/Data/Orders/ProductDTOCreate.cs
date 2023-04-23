
using System.ComponentModel.DataAnnotations;

public class ProductDTOCreate
{
    [Required]
    public Guid ProductGuid { get; set; }

    [Required]
    public String Title { get; set; }

    [Required]
    public String Description { get; set; }

    [Required]
    public double Price { get; set; }

    [Required]
    public DateTime PublishedDate { get; set; }

    public DateTime CreatedDate { get; set; }

}