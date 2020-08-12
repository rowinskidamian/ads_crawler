<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Total Ads Chart</title>
    <script src="https://cdn.jsdelivr.net/npm/chart.js@2.9.3/dist/Chart.bundle.min.js"></script>
</head>
<body>

<div id="chartContainer" style="height: 400px; width: 40%;">
    <canvas id="chartToDisplay"></canvas>
</div>


<script>
    const ctx = document.getElementById('chartToDisplay');
    let myChart = new Chart(ctx, {
        type: 'bar',
        data: {
            // labels: ["Red", "Blue", "Yellow", "Green", "Purple", "Orange"],
            labels: [${chartColumnNames}],
            datasets: [{
                label: '# of Votes',
                // data: [12, 19, 3, 5, 2, 3],
                data: [${chartColumnValue}],
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(54, 162, 235, 0.2)',
                    'rgba(255, 206, 86, 0.2)',
                    'rgba(75, 192, 192, 0.2)',
                    'rgba(153, 102, 255, 0.2)',
                    'rgba(255, 159, 64, 0.2)'
                ],
                borderColor: [
                    'rgba(255, 99, 132, 1)',
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 206, 86, 1)',
                    'rgba(75, 192, 192, 1)',
                    'rgba(153, 102, 255, 1)',
                    'rgba(255, 159, 64, 1)'
                ],
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: true
                    }
                }]
            }
        }
    });
</script>

</body>
</html>
