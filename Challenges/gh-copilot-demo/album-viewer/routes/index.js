var express = require("express");
var router = express.Router();
const axios = require("axios");

const Background = process.env.BACKGROUND_COLOR || "black";

/* GET home page. */
router.get("/", async function (req, res, next) {
  try {
    const url = `http://127.0.0.1:8080/albums`;
    console.log("Invoking album-api via dapr: " + url);
    axios.headers = { "Content-Type": "application/json" };
    var response = await axios.get(url);
    data = response.data || [];
    console.log("Response from backend albums api: ", data);
    res.render("index", {
      albums: data,
      background_color: Background,
    });
  } catch (err) {
    console.log("Error: ", err);
    next(err);
  }
});

module.exports = router;
