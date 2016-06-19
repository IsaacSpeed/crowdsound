<%@ page import="crowdsound.AuthController" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>CrowdSound</title>

    <asset:link rel="icon" href="favicon.ico" type="image/x-ico" />
    <script>
        $("#app").ready(function(){
            $("#artist2").hide();
            $("#artist3").hide();
            $("#artist4").hide();
            $("#artist5").hide();
            $("#genre2").hide();
            $("#genre3").hide();
            $("#genre4").hide();
            $("#genre5").hide();
            $("#artist1").focus(function(){
                $("#artist2").show();
            });
            $("#artist2").focus(function(){
                $("#artist3").show();
            });
            $("#artist3").focus(function(){
                $("#artist4").show();
            });
            $("#artist4").focus(function(){
                $("#artist5").show();
            });

            $("#genre1").focus(function(){
                $("#genre2").show();
            });
            $("#genre2").focus(function(){
                $("#genre3").show();
            });
            $("#genre3").focus(function(){
                $("#genre4").show();
            });
            $("#genre4").focus(function(){
                $("#genre5").show();
            });
        });
    </script>
</head>
<body>

<!-- Header -->
<section id="header">
    <div class="inner">
        <h3>Your party code is:</h3>
        <h1><strong>${code}</strong></h1>
    </div>
</section>
<section id="artistGenreChoice">
    <section id="musicSubmission" class="main style1">
        <div class="container">
            <div class="row 150%">
                <div class="6u 12u$(medium)">
                    <p>${message}</p>
                    <p>${errors}</p>
                    <g:form controller="User" action="adminview">
                        <h3>What's your name?</h3>
                        <input type="text" id="nickname" placeholder="It doesn't have to be your real name!"><br>
                        <h3>Pick your Top 5's</h3>
                        <p>Artists</p>
                        <input type="text" id="artist1" placeholder="Who's your favorite artist?"><br>
                        <input type="text" id="artist2" placeholder="Second favorite?"><br>
                        <input type="text" id="artist3" placeholder="Third?"><br>
                        <input type="text" id="artist4" placeholder="Fourth?!"><br>
                        <input type="text" id="artist5" placeholder="You've got a lot of artists in mind"><br>
                        <p>Genres</p>
                        <input type="text" id="genre1" placeholder="What's your favorite genre?"><br>
                        <input type="text" id="genre2" placeholder="Second favorite?"><br>
                        <input type="text" id="genre3" placeholder="Third?"><br>
                        <input type="text" id="genre4" placeholder="Didn't know they had this many genres"><br>
                        <input type="text" id="genre5" placeholder="You've got a broad taste in music"><br>
                        <g:hiddenField name="partyCode" value="${code}"/>
                        <g:actionSubmit value="Submit" action="adminview"/>
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
