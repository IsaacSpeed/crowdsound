<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>CrowdSound</title>

    <asset:link rel="icon" href="favicon.ico" type="image/x-ico" />

    <script>
    //todo fix this
        $(document).ready(function() {
            $("#start").hide();
            $("#stop").hide();
            if(isPartyStarted()) {
                $("#stop").show();
            } else {
                $("#start").show();
            }

            $("#start").click(function() {
                startParty();
                $("#stop").show();
                $("#start").hide();
            });

            $("#stop").click(function() {
                stopParty();
                $("#start").show()
                $("#stop").hide();
            });

            $("#end").click(function() {
                endParty();
                window.location.href = "http://crowdsound.us"
            });
        });

        function isPartyStarted() {
            var xhr = new XMLHttpRequest();
            xhr.open('GET', 'http://crowdsound.us/party/isStarted?partyCode=${partyCode}', true);
            xhr.send();

            xhr.onreadystatechange = processRequest;

            function processRequest(e) {
                if (xhr.readyState == 4 && xhr.status == 200) {
                    alert("Response: " + xhr.responseText);
                    alert(xhr.responseText === "true");
                    return (xhr.responseText === "true");
                }
            }
        }
        var frequency_list
        function getGenresAndArtistsFrequency() {
            var xhr = new XMLHttpRequest();
            xhr.open('GET', 'http://crowdsound.us/party/getGenresAndArtistsFrequency?partyCode=${partyCode}', true);
            xhr.send();

            xhr.onreadystatechange = processRequest();

            function processRequest(e) {
                if (xhr.readyState == 4 && xhr.status == 200) {
                    alert(xhr.responseText);
                    frequency_list = xhr.responseText;

                }
            }
        }

        function startParty() {
            var xhr = new XMLHttpRequest();
            xhr.open('GET', 'http://crowdsound.us/party/start?partyCode=${partyCode}&isPresentation=true', true);
            xhr.send();

            xhr.onreadystatechange = processRequest;

            function processRequest(e) {
                if (xhr.readyState == 4 && xhr.status == 200) {
                    var response = xhr.responseText;
                    alert(response);
                }
            }
        }

        function stopParty() {
            var xhr = new XMLHttpRequest();
            xhr.open('GET', 'http://crowdsound.us/party/stop?partyCode=${partyCode}', true);
            xhr.send();

            xhr.onreadystatechange = processRequest;

            function processRequest(e) {
                if (xhr.readyState == 4 && xhr.status == 200) {
                    var response = xhr.responseText;
                    alert(response);
                }
            }
        }

        function endParty() {
            var xhr = new XMLHttpRequest();
            xhr.open('GET', 'http://crowdsound.us/party/end?partyCode=${partyCode}', true);
            xhr.send();
            alert("Ending party!");

            xhr.onreadystatechange = processRequest;

            function processRequest(e) {
                if (xhr.readyState == 4 && xhr.status == 200) {
                    var response = xhr.responseText;
                    alert(response);
                }
            }
        }
    </script>
    <script src="http://d3js.org/d3.v3.min.js"></script>
    <script src="../../assets/d3.layout.cloud.js"></script>
</head>
<body>

<!-- Header -->
<section id="header">
    <div class="inner">
        <h3>Your party code is:</h3>
        <h1><strong>${params.partyCode}</strong></h1>
    </div>
</section>
<section id="queueView">
    <section id="partyView" class="main style1">
        <div class="container">
            <div class="row 150%">
                <div class="6u 12u$(medium)">
                    <button id="start" style="float: left">Start the party!</button>
                    <button id="stop" style="float: left">Pause Queueing</button>
                    <button id="end" style="float: right">END PARTY</button>
                    <iframe src="https://embed.spotify.com/?uri=spotify:user:${userId}:playlist:${playlistId}&theme=white" width="100%" height="300px" frameborder="0" allowtransparency="true"></iframe>
                </div>
            </div>
        </div>
    </section>
</section>
<section id="partyViewSection">
    <section id="partyView" class="main style2">
        <div class="container">
            <div class="row 150%">
                <div class="6u 12u$(medium)">
                    <script>
                        getGenresAndArtistsFrequency()
                        var cloudlist = JSON.parse(frequency_list).word_freq;
                        var color = d3.scale.linear()
                                .domain([0,1,2,3,4,5,6,10,15,20,100])
                                .range(["#ddd", "#ccc", "#bbb", "#aaa", "#999", "#888", "#777", "#666", "#555", "#444", "#333", "#222"]);

                        d3.layout.cloud().size([800, 300])
                                .words(cloudlist)
                                .rotate(0)
                                .fontSize(function(d) { return d.size; })
                                .on("end", draw)
                                .start();

                        function draw(words) {
                            d3.select("body").append("svg")
                                    .attr("width", 850)
                                    .attr("height", 350)
                                    .attr("class", "wordcloud main style2")
                                    .append("g")
                                    // without the transform, words words would get cutoff to the left and top, they would
                                    // appear outside of the SVG area
                                    .attr("transform", "translate(320,200)")
                                    .selectAll("text")
                                    .data(words)
                                    .enter().append("text")
                                    .style("font-size", function(d) { return d.size + "px"; })
                                    .style("fill", function(d, i) { return color(i); })
                                    .attr("transform", function(d) {
                                        return "translate(" + [d.x, d.y] + ")rotate(" + d.rotate + ")";
                                    })
                                    .text(function(d) { return d.text; });
                        }
                    </script>
                    <svg class="wordcloud">
                        <g transform="translate(320,200)">
                        </g>
                    </svg>
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
