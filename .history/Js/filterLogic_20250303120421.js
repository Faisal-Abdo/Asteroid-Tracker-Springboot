const userOption = document.getElementById("filterSelect").value;

const token = localStorage.getItem("token");

const myHeaders = new Headers();
myHeaders.append("Authorization", "Bearer ${token}");

const requestOptions = {
  method: "GET",
  headers: myHeaders,
};

const url = "";
const userInput = "";

if (userOption == "AsteroidByID") {
  userInput = document.getElementById("asteroidId").value;
  url = `"http://localhost:8080/getAsteroidByID?id=${userInput}"`;
} else if ((userOption = "AsteroidsByTodayDate")) {
  url = "http://localhost:8080/getAsteroidsByTodayDate";
} else if ((userOption = "getAsteroidsWithinDateRange")) {
  userInput = document.getElementById("startDate").value;
  const userInput1 = document.getElementById("endDate").value;
  url = `"http://localhost:8080/getAsteroidsWithinDateRange?startDate=${userInput}&endDate=${userInput1}"`;
} else if ((userOption = "getAsteroidsLargerThan")) {
  userInput = document.getElementById("size").value;
  url = `"http://localhost:8080/getAsteroidsLargerThan?size=${userInput}"`;
} else if ((userOption = "getPotentiallyHazardousAsteroids")) {
  url = "http://localhost:8080/getPotentiallyHazardousAsteroids";
} else if ((userOption = "getClosestAsteroidsByDistance")) {
  userInput = document.getElementById("topN").value;
  url = `"http://localhost:8080/getClosestAsteroidsByDistance?topN=${topN}"`;
} else if ((userOption = "getFastestAsteroids")) {
  userInput = document.getElementById("topN").value;
  url = `"http://localhost:8080/getFastestAsteroids?topN=${topN}"`;
} else if ((userOption = "getLargestAsteroids")) {
  userInput = document.getElementById("topN").value;
  url = `"http://localhost:8080/getLargestAsteroids?topN=${topN}"`;
}

fetch(`${url}`, requestOptions)
  .then((data) => {
    const nearEarthObjects = Object.values(data.nearEarthObjects).flat();

    const table = document.querySelector("table");
    const tableHead = document.querySelector("thead");
    const tableBody = document.querySelector("tbody");

    tableHead.innerHTML = "";
    tableBody.innerHTML = "";

    const tableData = document.createElement("td");
    const tableHeadRow = document.getElementById("mainTableHeadRow");

    nearEarthObjects.forEach((asteroid) => {
      const closeApproach = asteroid.closeApproachData[0]; //the array has one list

      const tableRow = document.createElement("tr");

      if (
        userOption == "getAsteroidsLargerThan" ||
        userOption == "getLargestAsteroids"
      ) {
        tableData.innerHTML = '<tr><th style="background-color: orange;">Size (km)</th></tr>';
            tableHeadRow.append(tableData)
        const rowData = [
          asteroid.id,
          `<a
        href="https://ssd.jpl.nasa.gov/tools/sbdb_lookup.html#/?sstr=${asteroid.id}&view=VOP"
        target="_blank"
      >
        ${asteroid.name}
      </a>`,
          closeApproach.closeApproachDate,
          closeApproach.missDistance.kilometers,
          closeApproach.missDistance.lunar,
          closeApproach.missDistance.miles,
          closeApproach.relativeVelocity.kilometersPerHour,
          asteroid.estimated_diameter.kilometers.estimated_diameter_max
        ];

        rowData.forEach((value) => {
          tableData.innerHTML = value;
          tableRow.appendChild(tableData);
        });

        displayNoOfRows();

        table.appendChild(tableRow);
      }
    });
  })
  .catch((error) => console.error(error));
