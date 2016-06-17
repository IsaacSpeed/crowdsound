<!doctype html>
<html>
<head>
    <title>CrowdSound - Authorize</title>
    <meta name="layout" content="main">
    <g:if env="development"><asset:stylesheet src="main.css"/></g:if>
</head>
<body>
<g:form controller="auth" action="save">
    <Label>Token: </Label>
    <g:textfield name="token"/>
</g:form>
</body>
</html>