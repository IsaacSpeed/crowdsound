<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>CrowdSound</title>

    <asset:link rel="icon" href="favicon.ico" type="image/x-ico" />
</head>
<body>

<!-- Header -->
<section id="header">
    <div class="inner">
        <h3>Your party code is:</h3>
        <h1><strong>${params.partyCode}</strong></h1>
    </div>
</section>
<section id="partyViewSection">
    <section id="partyView" class="main style1">
        <div class="container">
            <div class="row 150%">
                <div class="6u 12u$(medium) row">
                    <h1>Artists:</h1>
                    <g:each in="${artists}">
                        ${it}<br/>
                    </g:each>
                    <h1>Genres:</h1>
                    <g:each in="${genres}">
                        ${it}<br/>
                    </g:each>
                </div>
                <iframe style="float: right" src="https://embed.spotify.com/?uri=spotify:user:${userId}:playlist:${playlistId}&theme=white" width="100%" height="50%" frameborder="0" allowtransparency="true"></iframe>
            </div>
        </div>
    </section>
</section>
<!-- Footer -->
<section id="footer">
    <ul class="icons">
        <li><a href="#" class="icon alt fa-twitter"><span class="label">Twitter</span></a></li>
        <li><a href="#" class="icon alt fa-facebook"><span class="label">Facebook</span></a></li>
        <li><a href="#" class="icon alt fa-instagram"><span class="label">Instagram</span></a></li>
        <li><a href="#" class="icon alt fa-github"><span class="label">GitHub</span></a></li>
        <li><a href="#" class="icon alt fa-envelope"><span class="label">Email</span></a></li>
    </ul>
    <ul class="copyright">
        <li>&copy; Colin, Daniel, Isaac, Steven</li><li>Design: <a href="http://html5up.net">HTML5 UP</a></li>
    </ul>
</section>
</body>
</html>
