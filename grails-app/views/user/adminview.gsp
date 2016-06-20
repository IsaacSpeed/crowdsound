<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>CrowdSound</title>

    <asset:link rel="icon" href="favicon.ico" type="image/x-ico" />

    <script>
        /*addSong();

        function addSong() {
            var xhr = new XMLHttpRequest();
            xhr.open('GET', 'http://crowdsound.us/party/addSong?partyCode=${partyCode}', true);
            xhr.send();

            xhr.onreadystatechange = processRequest;

            function processRequest(e) {
                if (xhr.readyState == 4 && xhr.status == 200) {
                    var duration = xhr.responseText;
                    alert(duration);
                    window.setTimeout(addSong, parseInt(duration - 200));
                }
            }
        }*/
    </script>
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
                <div class="6u 12u$(medium)">
                    <g:form controller="party" action="start">
                        <g:hiddenField name="partyCode" value="${params.partyCode}"/>
                        <g:submitButton name="startparty" value="Start the party!"/>
                    </g:form>
                    <h1 id="songCountdown"></h1>
                    <h1>This page is different from the normal party view, to allow admin privileges</h1>
                    <h2>Information about the party can go here such as:</h2>
                    <p>Other user info</p>
                    <p>Current song playing</p>
                    <p>Like/Dislike song</p>
                    <p>See song queue</p>
                </div>
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
