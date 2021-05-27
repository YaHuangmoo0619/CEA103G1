<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>


<!DOCTYPE html>
<<html>
<head>
    <meta charset='UTF-8'>
    <title>CodePen Demo</title>
    <meta name="robots" content="noindex">
    <link rel="shortcut icon" type="image/x-icon" href="https://cpwebassets.codepen.io/assets/favicon/favicon-aec34940fbc1a6e787974dcd360f2c6b63348d4b1f4e06c77743096d55480f33.ico">
    <link rel="mask-icon" type="" href="https://cpwebassets.codepen.io/assets/favicon/logo-pin-8f3771b1072e3c38bd662872f6b673a722f4b3ca2421637d5596661b4e2132cc.svg" color="#111">
    <link rel="canonical" href="https://codepen.io/lrwgupzs/pen/rNyzoNz?editors=1010">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
    <style class="INLINE_PEN_STYLESHEET_ID">
        @import url(https://fonts.googleapis.com/css?family=Ubuntu:300,400,500,700,300italic,400italic,500italic,700italic);

@import url(https://fonts.googleapis.com/css?family=Roboto:400,100italic,100,300,300italic,400italic,500,500italic,700,700italic,900,900italic);

body{
  background:#E2E2E2;
}

.card{
  width:600px;
  height:360px;
  background:#FFF;
  margin:0 auto;
  border-radius:20px;
  margin-top:20px;
}

.top{
  width:100%;
  background:rgb(25, 146, 250);
  padding:57px 0;
  border-top-left-radius:20px;
  border-top-right-radius:20px;
  position:relative;
}

.payment{
  position: absolute;
  width: 30px;
  font-size: 22px;
  color: #FFF;
  font-family: 'ubuntu', sans-serif;
  text-transform: uppercase;
  line-height: 25px;
  font-weight: 500;
  top:32px;
  left:35px;
}

.visa svg{
  width: 120px;
  height: auto;
  position: absolute;
  right: 29px;
  top: -195px;
}


/*form styles*/
form{
  width:550px;
  margin:0 auto;

}

.one{
  width:225px;
}

.two{
  width:245px;
}
.one, .two, .three, .four, .five{
  float:left;
  margin:15px 40px 5px 0;
}

.three, .four, .five{
  float:left;
  width:143px;
  
}

input, select{
  clear:both;
  float:none;
  display:block;
  -webkit-appearance: none;
  border:1px solid rgb(197, 197, 197);
  padding:14px 15px;
  border-radius:3px;
  width:100%;
  font-size:15px;
 color: #000;
 text-align: left;
 font-weight: bold;
  background:#FFF;
}

input{
  color: rgb(244, 195, 88);
    text-shadow: 0px 0px 0px #000;
    -webkit-text-fill-color: transparent;
}

select{
  padding-left:15px;
}

::-webkit-input-placeholder {
 font-size: 15px;
 color: #000;
 text-align: left;
 font-weight: bold;
  padding-left:0px;
}
:-moz-placeholder { /* older Firefox*/
font-size: 15px;
 color: #000;
 text-align: left;
 font-weight: bold;
  padding-left:0px;
}
::-moz-placeholder { /* Firefox 19+ */ 
font-size: 15px;
 color: #000;
 text-align: left;
 font-weight: bold;
  padding-left:0px;
} 
:-ms-input-placeholder { 
 font-size: 15px;
 color: #000;
 text-align: left;
 font-weight: bold;
  padding-left:0px;
}

label{
  color:rgb(151, 151, 151);
  font-size:12px;
  font-weight:500;
  letter-spacing:.-0px;
  font-family:'ubuntu', sans-serif;
  text-transform:uppercase;
  margin:15px 0;
  display:block;
}
 
input[type=text]:focus,input[type=number]:focus, textarea:focus, select:focus {
  box-shadow: none;
  outline:none;
  border: 1px solid rgb(25, 146, 250);
}



/*drop down arrow */
/* Targetting Webkit browsers only. FF will show the dropdown arrow with so much padding. */
@media screen and (-webkit-min-device-pixel-ratio:0) {
    select {padding-right:18px}
}

label {position:relative}
.three label:after, .four label:after {
    content:"\f078";   
    font-family: "FontAwesome";
    font-size: 11px;
    color:rgb(140, 140, 140);
    right:10px; top:45px;
    padding:0 0 2px;
    position:absolute;
    pointer-events:none;
}
label:before {
    content:'';
    right:4px; top:0px;
    width:23px; height:18px;
    background:#fff;
    position:absolute;
    pointer-events:none;
    display:block;
}






/* Info */
.info {
  width: 300px;
  margin: 35px auto;
  text-align: center;
  font-family: 'roboto', sans-serif;
}

.info h1 {
  margin: 0;
  padding: 0;
  font-size: 28px;
  font-weight: 400;
  color: #333333;
  padding-bottom: 5px;

}
.info span {
  color:#666666;
  font-size: 13px;
  margin-top:20px;
}
.info span a {
  color: #666666;
  text-decoration: none;
}
.info span .fa {
  color: rgb(226, 168, 16);
  font-size: 19px;
  position: relative;
  left: -2px;
}

.info span .spoilers {
  color: #999999;
  margin-top: 5px;
  font-size: 10px;
}
  </style>
    <script src="https://cpwebassets.codepen.io/assets/editor/iframe/iframeConsoleRunner-d8236034cc3508e70b0763f2575a8bb5850f9aea541206ce56704c013047d712.js"></script>
    <script src="https://cpwebassets.codepen.io/assets/editor/iframe/iframeRefreshCSS-4793b73c6332f7f14a9b6bba5d5e62748e9d1bd0b5c52d7af6376f3d1c625d7e.js"></script>
    <script src="https://cpwebassets.codepen.io/assets/editor/iframe/iframeRuntimeErrors-4f205f2c14e769b448bcf477de2938c681660d5038bc464e3700256713ebe261.js"></script>
</head>

<body>
    <div class="card">
        <div class="top">
            <div class="payment">«H¥Î¥d</div>
            <div class="visa">
                <svg xmlns="http://www.w3.org/2000/svg" version="1.0" width="1000.000000pt" height="380.000000pt" viewBox="0 0 1000.000000 380.000000" preserveAspectRatio="xMidYMid meet">
                    <g transform="translate(0.000000,380.000000) scale(0.100000,-0.100000)" fill="#FFF" stroke="none">
                        <path d="M5960 3289 c-241 -23 -478 -105 -645 -224 -83 -58 -195 -179 -243 -261 -102 -172 -135 -390 -86 -564 40 -141 155 -290 310 -402 44 -31 163 -101 266 -157 220 -117 262 -145 316 -206 89 -101 70 -232 -46 -315 -187 -136 -611 -111 -969 55 l-92 43 -11 -67 c-6 -36 -28 -171 -49 -299 l-38 -233 28 -14 c47 -25 230 -74 355 -95 834 -143 1482 153 1589 725 35 184 8 361 -77 502 -75 127 -252 270 -463 375 -241 121 -362 204 -397 271 -25 49 -27 112 -4 155 23 44 92 99 153 122 178 67 498 41 735 -60 39 -17 73 -29 75 -26 4 4 93 539 93 562 0 16 -157 67 -275 88 -187 35 -347 42 -525 25z" />
                        <path d="M2778 2778 c-97 -260 -249 -672 -339 -915 -90 -244 -166 -443 -169 -443 -3 0 -21 78 -39 173 -41 207 -62 271 -135 414 -171 334 -445 630 -783 847 -40 25 -73 41 -73 36 0 -5 132 -510 294 -1122 162 -612 300 -1134 306 -1161 l12 -47 371 2 371 3 463 1135 c254 624 500 1228 547 1343 l85 207 -368 0 -368 0 -175 -472z" />
                        <path d="M3761 1928 c-117 -728 -216 -1335 -218 -1350 l-5 -28 346 0 346 0 5 23 c3 12 100 609 215 1327 116 718 212 1315 215 1328 l5 22 -348 0 -348 0 -213 -1322z" />
                        <path d="M7892 3235 c-71 -16 -147 -59 -179 -103 -11 -15 -32 -50 -46 -77 -24 -50 -1047 -2490 -1047 -2500 0 -3 163 -5 363 -5 l362 0 74 205 73 205 448 0 448 0 10 -47 c6 -27 25 -119 43 -205 l33 -158 321 0 322 0 -44 213 c-24 116 -151 723 -281 1347 l-238 1135 -299 2 c-224 1 -316 -2 -363 -12z m278 -1215 c55 -266 100 -490 100 -497 0 -10 -59 -13 -289 -13 l-289 0 70 188 c38 103 121 331 185 506 65 175 118 315 120 310 1 -5 48 -227 103 -494z" />
                        <path d="M9285 856 c-111 -49 -121 -200 -18 -267 39 -26 118 -25 161 1 43 27 76 99 68 149 -16 97 -122 156 -211 117z m116 -21 c87 -45 92 -179 9 -229 -78 -45 -177 5 -187 95 -12 106 87 180 178 134z" />
                    </g>
                </svg>
            </div>
        </div>
        <div class="card-body">
            <form>
                <div class="one">
                    <label for=''>Name on card</label>
                    <input placeholder='Johny Relative' type='text'>
                </div>
                <div class="two">
                    <label for=''>Card Number</label>
                    <input maxlength='16' placeholder='4478 6632 9923 8890' type='number'>
                </div>
                <div class="three">
                    <label for=''>Expiry Date</label>
                    <select>
                        <option>January</option>
                        <option>February</option>
                        <option>March</option>
                        <option>April</option>
                        <option>May</option>
                        <option>June</option>
                        <option>July</option>
                        <option>August</option>
                        <option>September</option>
                        <option>October</option>
                        <option>November</option>
                        <option>December</option>
                    </select>
                </div>
                <div class="four">
                    <!-- blank character -->
                    <label for=''>&#x200b;</label>
                    <select>
                        <option>2015</option>
                        <option>2016</option>
                        <option>2017</option>
                        <option>2018</option>
                        <option>2019</option>
                        <option>2020</option>
                    </select>
                </div>
                <div class="five">
                    <label for=''>CVV</label>
                    <input maxlength='3' placeholder='633' type='number'>
                </div>
            </form>
        </div>
    </div>
    <script src="https://cpwebassets.codepen.io/assets/common/stopExecutionOnTimeout-157cd5b220a5c80d4ff8e0e70ac069bffd87a61252088146915e8726e5d9f147.js"></script>
    
    <script src="https://cdpn.io/cp/internal/boomboom/pen.js?key=pen.js-24acc41f-2fcb-547e-cd50-91ab93a2737c" crossorigin></script>
	
</body>

</html>
 
