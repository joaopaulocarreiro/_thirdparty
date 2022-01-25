using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using saborearacores.pt.Models;

namespace saborearacores.pt.Controllers
{
    public class RestaurantsController : Controller
    {
        RestaurantDBContext db = new RestaurantDBContext();

        /// <summary>
        /// Default behaviour for the restaurant controller.
        /// Return all restaurants in database.
        /// This method will return a view with all the restaurants
        /// in the database.
        /// </summary>
        /// <returns></returns>
        public ActionResult Index()
        {
            var movies = from m in db.Restaurants select m;
            return View(movies.ToList());
        }

        /// <summary>
        /// Method to Create a new Restaurant.
        /// This method returns a view that allows users to create new items
        /// of type restaurant.
        /// </summary>
        /// <returns></returns>
        public ActionResult Create()
        {
            return View();
        }

        /// <summary>
        /// Method to create a new effectivãly create a new restaurant.
        /// </summary>
        /// <param name="newRestaurant"></param>
        /// <returns></returns>
        [HttpPost]
        public ActionResult Create(Restaurant newRestaurant)
        {
            if (ModelState.IsValid)
            {
                db.Restaurants.Add(newRestaurant);
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            else
            {
                return View(newRestaurant);
            }
        }
    }
}