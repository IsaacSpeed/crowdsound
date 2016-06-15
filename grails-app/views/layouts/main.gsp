<!DOCTYPE HTML>
<!--
	Photon by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
<head>
	<title><g:layoutTitle default="CrowdSound"/></title>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<!--[if lte IE 8]><link rel="stylesheet" href="${resource(dir: 'css', file: 'html5shiv.css')}" type="text/css"><![endif]-->
	<link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">
	<!--[if lte IE 8]><link rel="stylesheet" href="${resource(dir: 'css', file: 'ie8.css')}" type="text/css"><![endif]-->
	<!--[if lte IE 9]><link rel="stylesheet" href="${resource(dir: 'css', file: 'ie9.css')}" type="text/css"><![endif]-->

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
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

	<g:layoutHead/>
	<r:layoutResources />
</head>
<body>

	<!-- Header -->
	<section id="header">
		<div class="inner">
			<span class="icon major fa-cloud"></span>
			<h1>Welcome to <strong>CrowdSound</strong>.</h1>
			<p>We're working on making this app dope af, stay tuned.</p>
		</div>
	</section>
	<section id="app">
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
					<!--
					<g:form controller="Spotify" action="auth">
						<input type="text" id="hostUsername" name="username" placeholder="username"><br>
                        <input type="password" id="hostPassword" name="password" placeholder="password"><br>
						<g:actionSubmit value="Sign In"/>
					</g:form>
					-->
						<g:form controller="person" action="save">
							<label>First Name: </label>
							<g:textField name="firstName"/><br/>
							<label>Last Name: </label>
							<g:textField name="lastName"/><br/>
							<label>Age: </label>
							<g:textField name="age"/><br/>
							<g:actionSubmit value="Save"/>
						</g:form>
					</div>
				</div>
			</div>
		</section>
		<section id="userLogin" class="main style1">
			<div class="container">
				<div class="row 150%">
					<div class="6u 12u$(medium)">
						<form>
							<input type="text" id="userCode" placeholder="Enter a Party Code"><br>
							<input type="submit" value="Let's Party!">
						</form>
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

	<!-- Scripts -->
	<script src="${resource(dir: 'js', file: 'jquery.min.js')}"></script>
	<script src="${resource(dir: 'js', file: 'jquery.scrolly.min.js')}"></script>
	<script src="${resource(dir: 'js', file: 'skel.min.js')}"></script>
	<script src="${resource(dir: 'js', file: 'util.js')}"></script>
	<!--[if lte IE 8]><script src="${resource(dir: 'js', file: 'respond.min.js')}"></script><![endif]-->
	<script src="${resource(dir: 'js', file: 'main.js')}"></script>
	<r:layoutResources />
</body>
</html>