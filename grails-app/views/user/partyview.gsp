<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>CrowdSound</title>

    <asset:link rel="icon" href="favicon.ico" type="image/x-ico" />

    <script>
        function reloadPlaylist() {
            document.getElementById('spotifyPlayer').src = document.getElementById('spotifyPlayer').src;
            $("svg").remove();
            doWordCloud();
        }

        function getGenresAndArtistsFrequency() {
            var xhr = new XMLHttpRequest();
            xhr.open('GET', 'http://crowdsound.us/party/getGenresAndArtistsFrequency?partyCode=${partyCode}', false);
            xhr.send();

            if (xhr.readyState == 4 && xhr.status == 200) {
                return xhr.responseText;
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
                    <!-- todo make this occur on an interval -->
                    <button id="refreshPlaylist" onclick="reloadPlaylist()">Refresh</button>
                    <iframe id="spotifyPlayer" src="https://embed.spotify.com/?uri=spotify:user:${userId}:playlist:${playlistId}&theme=white" width="100%" height="300px" frameborder="0" allowtransparency="true"></iframe>
                </div>
            </div>
        </div>
    </section>
</section>
<section id="partyViewSection">
    <script>
        function doWordCloud() {
            var frequency_list = getGenresAndArtistsFrequency();
            var cloudlist = JSON.parse(frequency_list).word_freq;
            var color = d3.scale.linear()
                .domain([0, 1, 2, 3, 4, 5, 6, 10, 15, 20, 100])
                .range(["#ddd", "#ccc", "#bbb", "#aaa", "#999", "#888", "#777", "#666", "#555", "#444", "#333", "#222"]);

            d3.layout.cloud()
                .size([500,500])
                .words(cloudlist)
                .rotate(0)
                .fontSize(function(d) {
                    return d.size;
                })
                .on("end", draw)
                .start();

            function draw(words) {
                d3.select("body").append("svg")
                    .attr("class", "wordcloud")
                    .attr("width", d3.layout.size()[0])
                    .attr("height", d3.layout.size()[1])
                    .append("g")
                    .attr("transform", "translate(" + d3.layout.size()[0] / 2 + "," + d3.layout.size()[1] / 2 + ")")
                    .selectAll("text")
                    .data(words)
                    .enter().append("text")
                    .style("font-size", function(d) {
                        return d.size + "px";
                    })
                    .style("fill", function(d, i) {
                        return color(i);
                    })
                    .attr("transform", function(d) {
                        return "translate(" + [d.x, d.y] + ")rotate(" + d.rotate + ")";
                    })
                    .text(function(d) {
                        return d.text;
                    });
            }
        }
        doWordCloud();
    </script>
</section><!-- Footer -->
</body>
</html>
