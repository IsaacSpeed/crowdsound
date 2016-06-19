<!doctype html>
<html>
<head>
    <title>CrowdSound</title>
    <meta name="layout" content="main">
    <asset:stylesheet src="main.css" />
</head>

<body>
    <g:each in="${tracks}">
        <p>Song name: ${it.getName()}</p>
        <p>Artist: ${it.getArtists().get(0).getName()}</p>
        <p>is explicit? ${it.isExplicit()}</p>
        <br/>
    </g:each>
</body>
</html>