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
<section id="queueView">
    <section id="partyView" class="main style1">
        <div class="container">
            <div class="row 150%">
                <div class="6u 12u$(medium)">
                    <iframe src="https://embed.spotify.com/?uri=spotify:user:${userId}:playlist:${playlistId}&theme=white" width="100%" height="50%" frameborder="0" allowtransparency="true"></iframe>
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
                        var frequency_list = getGenresAndArtistsFrequency();
                        var cloudlist = JSON.parse(frequency_list).word_freq;
                        var color = d3.scale.linear()
                                .domain([0,1,2,3,4,5,6,10,15,20,100])
                                .range(["#ddd", "#ccc", "#bbb", "#aaa", "#999", "#888", "#777", "#666", "#555", "#444", "#333", "#222"]);

                        d3.layout.cloud()
                                .words(cloudlist)
                                .rotate(0)
                                .fontSize(function(d) { return d.size; })
                                .on("end", draw)
                                .start();

                        function draw(words) {
                            d3.select("body").append("svg")
                                    .attr("height", 500)
                                    .attr("class", "wordcloud")
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
