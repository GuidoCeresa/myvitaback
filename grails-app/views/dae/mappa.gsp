<%--
  Created by IntelliJ IDEA.
  User: Gac
  Date: 31-3-13
  Time: 08:38
  To change this template use File | Settings | File Templates.
--%>

<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main">

    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript">
        google.load("visualization", "1", {packages: ["map"]});
        google.setOnLoadCallback(drawMap);
        function drawMap() {
            %{--var data=google.visualization.arrayToDataTable("${myMappa}");--}%

            var data = google.visualization.arrayToDataTable([
                ['Lat', 'Lon', 'Name'],
                <g:each in="${myLat}" status="i" var="nonServe">
                [${myLat[i]}, ${myLon[i]}, "${myTip[i]}"] ,
                </g:each>
            ]);

            var map = new google.visualization.Map(document.getElementById('map_div'));
            map.draw(data, {showTip: true});
        }
    </script>
    <title>${myComune}</title>
</head>

<body>
${myTitolo}
<div id="map_div" style="height: 700px"></div>
</body>
</html>