<!DOCTYPE html>
<html lang="en">
<head>
    <title>Restaurants</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://bootswatch.com/4/minty/bootstrap.min.css" >

    <!-- Custom styles for this template -->
    <link href="css/style.css" rel="stylesheet">

    <!-- Favicon -->
    <link rel="shortcut icon" type="image/png" href="http://elowcarbfoodlist.org/wp-content/uploads/2013/01/bacon-256.jpg"/>

</head>
<body>
<!-- Navigation bar -->
<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
    <a class="navbar-brand" href="#">Restaurants</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
//AFEGIR LINK CAP A RESTAPI
    <div class="collapse navbar-collapse" id="navbarsExampleDefault">
        <ul class="navbar-nav mr-auto">
            <li  class="nav-item">
                <a text-align="right" class="nav-link" href="test">Inici</a>
            </li>
            <li  class="nav-item">
                <a text-align="right" class="nav-link" href="test">RestApi</a>
            </li>

        </ul>

    </div>
</nav>

<main role="main">

    <!-- Main jumbotron for a primary marketing message or call to action -->
    <div class="jumbotron">
        <div class="container">
            <h1 class="display-3">Demana el que et demani el cos</h1>
            <h2>Menjar a domicili a prop de tu</h2>
            <p><a class="btn btn-primary btn-lg" href="test" role="button">Consultar més &raquo;</a></p>
        </div>
    </div>
<#if name??>
<h1>Hola ${name!"yuhu"}</h1>
</#if>
<#if restaurants??>
    <#list restaurants as item>
    <div class="card" style="width: 20rem;">
        <img class="card-img-top" src="${item.images!""}" alt="Card image cap">
        <div class="card-block">
            <h4 class="card-title">${item.name!""}</h4>
            <p class="card-text">Telf: ${item.numero!""}</p>
            <p class="card-text">Adreça:<br> ${item.address!""}</p>
            <p class="card-text">Web: ${item.website!""}</p>
            <p class="card-text">Estil ${item.type!""}</p>
            <p class="card-text">♥ ${item.mitjana!""} ♥</p>
            <p class="card-text">Codi del Restaurant: ${item.code!""}</p>
        </div>
    </div>
    </#list>
</#if>
</body>
</html>