using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Data.Entity;
using System.ComponentModel.DataAnnotations;

namespace saborearacores.pt.Models
{
    public class Restaurant
    {
       public int ID { get; set; }

       [Required(ErrorMessage = "Nome é um campo obrigatório")]
       public string NameOf { get; set; }

       [Required(ErrorMessage = "Morada é um campo obrigatório")]
       public string Address { get; set; }
    }

    public class RestaurantDBContext : DbContext
    {
        public DbSet<Restaurant> Restaurants { get; set; }
    }
}