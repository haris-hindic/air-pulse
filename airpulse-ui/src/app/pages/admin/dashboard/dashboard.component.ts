import { Component } from '@angular/core';
import { ChartData } from '../../shared/model/api-response';
import { FlightService } from '../services/flight.service';
import { LoaderService } from '../../shared/services/loader.service';
import { PrimeIcons } from 'primeng/api';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {


  chartData: any;

  chartOptions: any;

  pieData: any;
  pieOptions: any;

  pieChartData!: ChartData;
  lineChartData!: ChartData;

  constructor(private flightService: FlightService, private loader: LoaderService) {

  }


  loadChartData() {
    this.flightService.flightsByCity().subscribe(
      {
        next: (result) => {
          this.pieChartData = result;
        }, error: () => {
          this.loader.hide();
        }
      }
    );
    this.flightService.flightsByMonths().subscribe(
      {
        next: (result) => {
          this.lineChartData = result;
          this.initChart();
        }, error: () => {
          this.loader.hide();
        }
      }
    );
  }
  ngOnInit() {
    this.loadChartData();

  }

  initChart() {
    const documentStyle = getComputedStyle(document.documentElement);
    const textColor = documentStyle.getPropertyValue('--text-color');
    const textColorSecondary = documentStyle.getPropertyValue('--text-color-secondary');
    const surfaceBorder = documentStyle.getPropertyValue('--surface-border');

    this.chartData = {
      labels: this.lineChartData.chartLabels,
      datasets: [
        {
          label: 'Flights per months',
          data: this.lineChartData.chartValues,
          fill: false,
          backgroundColor: this.randomRGB(),
          borderColor: this.randomRGB(),
          tension: .4
        },
      ]
    };

    this.chartOptions = {
      plugins: {
        legend: {
          labels: {
            color: textColor
          }
        }
      },
      scales: {
        x: {
          ticks: {
            color: textColorSecondary
          },
          grid: {
            color: surfaceBorder,
            drawBorder: true
          }
        },
        y: {
          ticks: {
            color: textColorSecondary
          },
          grid: {
            color: surfaceBorder,
            drawBorder: true
          }
        }
      }
    };


    this.pieData = {
      labels: this.pieChartData.chartLabels,
      datasets: [{
        label: 'Departing fligts per city',
        data: this.pieChartData.chartValues,
        backgroundColor: this.getRandomColors(this.pieChartData.chartLabels.length),
        borderColor: this.getRandomColors(this.pieChartData.chartLabels.length),
      }]
    };

    this.pieOptions = {
      plugins: {
        legend: {
          labels: {
            fontColor: textColor
          }
        }
      },
      scales: {
        x: {
          ticks: {
            color: textColorSecondary,
            font: {
              weight: 500
            }
          },
          grid: {
            display: false,
            drawBorder: false
          }
        },
        y: {
          ticks: {
            color: textColorSecondary
          },
          grid: {
            color: surfaceBorder,
            drawBorder: false
          }
        },
      }
    };
  }
  getRandomColors(length: number) {
    const colors = [];
    for (let index = 0; index < length; index++) {
      colors.push(this.randomRGB());
    }
    return colors;
  }


  randomNum = () => Math.floor(Math.random() * (235 - 52 + 1) + 52);

  randomRGB = () => `rgb(${this.randomNum()}, ${this.randomNum()}, ${this.randomNum()})`;
}
