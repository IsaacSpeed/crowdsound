<!doctype html>
<html>
<head>
    <title>CrowdSound</title>
    <meta name="layout" content="main">
    <asset:stylesheet src="main.css" />
</head>

<body>
    <g:if test="${trackName}">
        The song you searched for was <i>${trackName}</i>. Deal with it
    </g:if>
    <g:else>
        <g:form name="findTrack" controller="Spotify" action="findSongs">
            <label>Enter a track name: </label>
            <g:textField name="trackName" value="track name"/>
            <g:submitButton name="Search" value="Search"/>
        </g:form>
    </g:else>
</body>
</html>