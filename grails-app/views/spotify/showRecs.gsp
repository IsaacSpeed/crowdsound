<!doctype html>
<html>
<head>
    <title>CrowdSound</title>
    <meta name="layout" content="main">
    <asset:stylesheet src="main.css" />
</head>

<body>
    <g:each in="${tracks}">
        <p>Song name: ${it.getName()}</p><br/>
        <p>Artist: ${it.getArtists()}</p><br/>
        <p>is explicit? ${it.isExplicit()}</p><br/>
        <br/>
    </g:each>
</body>
</html>