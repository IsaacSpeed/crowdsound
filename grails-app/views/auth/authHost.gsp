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
        <h1><strong>${partyCode}</strong></h1>
    </div>
</section>
<section id="artistGenreChoice">
    <section id="musicSubmission" class="main style1">
        <div class="container">
            <div class="row 150%">
                <div class="6u 12u$(medium)">
                    <g:form controller="User" action="adminview">
                        <h3 style="margin-top: -50px"><strong>What's your name?</strong></h3>
                        <input type="text" id="nickname" placeholder="It doesn't have to be your real name!"><br>

                        <h3 style="margin-top: 10px"><strong>Enter up to 5 of each:</strong></h3>
                        <div style="width: 100%; display: inline-flex; margin-top: -30px">
                            <div style="margin-right: 100px; margin-left: 50px">
                                <p>Artists</p>
                                <g:textField name="artist1"/><br/>
                                <g:textField name="artist2"/><br/>
                                <g:textField name="artist3"/><br/>
                                <g:textField name="artist4"/><br/>
                                <g:textField name="artist5"/><br/>
                            </div>

                            <div>
                                <p>Genres</p>
                                <g:select name="genres" size="7"
                                          noSelection="${['null':'Select Genres...']}"
                                          from='${availableGenres}'
                                          multiple="true"/>
                                <g:hiddenField name="partyCode" value="${partyCode}"/>
                            </div>
                        </div>
                        <g:actionSubmit value="Submit" action="adminview"/>
                        <br/>
                    </g:form>

                </div>
            </div>
        </div>
    </section>
</section>
<!-- Footer -->
</body>
</html>
