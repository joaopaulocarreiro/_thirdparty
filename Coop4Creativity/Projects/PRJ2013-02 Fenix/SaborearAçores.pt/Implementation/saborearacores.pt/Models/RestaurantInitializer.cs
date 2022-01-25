using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Data.Entity;

namespace saborearacores.pt.Models
{
    public class RestaurantInitializer : DropCreateDatabaseIfModelChanges<RestaurantDBContext>
    {
        protected override void Seed(RestaurantDBContext context)
        {
            var Restaurants = new List<Restaurant>();
            Restaurants.ForEach(d => context.Restaurants.Add(d));
        }
    }

}