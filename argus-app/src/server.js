const express = require("express");
const mysql = require("mysql");

const app = express();
const PORT = process.env.PORT || 63623;

const conn = mysql.createConnection({
  host: "localhost";
  port: 63623;
  user: "root";
  password: "1234";
  database: "argus";
});

app.use(express.json());
app.use(express.urlencode({extended: true}));
app.use(express.static("public"));

connection.connect(function(error) {
  console.log("Connect to db");

  app.listen(PORT, function() {
    console.log("Listtening at port " + PORT);
  });
});

/*
const { Client } = require('pg');

const client = new Client({
  connectionString: process.env.DATABASE_URL,
  ssl: {
    rejectUnauthorized: false
  }
});

client.connect();

client.query('SELECT table_schema,table_name FROM information_schema.tables;', (err, res) => {
  if (err) throw err;
  for (let row of res.rows) {
    console.log(JSON.stringify(row));
  }
  client.end();
});
*/
