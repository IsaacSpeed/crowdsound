<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>CrowdSound</title>

    <asset:link rel="icon" href="favicon.ico" type="image/x-ico" />
    <script>
        $("#app").ready(function(){
            $("#hostLogin").hide();
            $("#userLogin").hide();
            $("#hostButton").click(function(){
                $("#hostOrUser").hide();
                $("#hostLogin").show();
            });
            $("#userButton").click(function(){
                $("#hostOrUser").hide();
                $("#userLogin").show();
            });
            $('#hostLogin').click(function(){
            });
        });
    </script>
</head>
<body>

    <!-- Header -->
    <section id="header">
            <h1><strong>CrowdSound</strong></h1>
    </section>
    <section id="app" class="align-center">
        <section id="hostOrUser" class="main style1">
            <div class="container">
                <div class="row 150%">
                    <div class="6u 12u$(medium)">
                        <a id="hostButton" class="button">Host a Party</a>
                        <p>or</p>
                        <a id="userButton" class="button">Join One</a>
                    </div>
                </div>
            </div>
        </section>
        <section id="hostLogin" class="main style1">
            <div class="container">
                <div class="row 150%">
                    <div class="6u 12u$(medium)">
                        <h3>Login with your Spotify account:</h3>
                        <a class="button" href="https://accounts.spotify.com/authorize?client_id=cfa363d44d2743ab8f6dd82a6e7eaca8&response_type=code&redirect_uri=http://crowdsound.us/auth/authHost&scope=playlist-modify-private playlist-read-private playlist-modify-public">Login</a>
                    </div>
                </div>
            </div>
        </section>
        <section id="userLogin" class="main style1">
            <div class="container">
                <div class="row 150%">
                    <div class="6u 12u$(medium)">
                        <g:form controller="Auth" action="authUser">
                            <input type="text" id="userCode" name="userCode" placeholder="Enter a Party Code"><br>
                            <g:actionSubmit value="Join" action="authUser"/>
                        </g:form>
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
