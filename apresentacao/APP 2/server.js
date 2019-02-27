'use strict';

const express = require('express');

// Constants
const PORT = process.env.PORT || 8080;
const HOST = '0.0.0.0';

// App
console.log(process.env.PORT);
const app = express();
app.get('/', (req, res) => {
  res.send('Olá ' + (process.env.NAME || 'world'));
});

app.listen(PORT, HOST);
console.log(`Running on http://${HOST}:${PORT}`);