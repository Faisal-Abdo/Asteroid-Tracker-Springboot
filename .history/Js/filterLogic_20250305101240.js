// const userOption = document.getElementById("filterSelect").value;

// const token = localStorage.getItem("token");

// const myHeaders = new Headers();
// myHeaders.append("Authorization", "Bearer ${token}");

// const requestOptions = {
//   method: "GET",
//   headers: myHeaders,
// };

// const url = "";
// const userInput = "";

// if (userOption == "AsteroidByID") {
//   userInput = document.getElementById("asteroidId").value;
//   url = `"http://localhost:8080/getAsteroidByID?id=${userInput}"`;
// } else if ((userOption = "AsteroidsByTodayDate")) {
//   url = "http://localhost:8080/getAsteroidsByTodayDate";
// } else if ((userOption = "getAsteroidsWithinDateRange")) {
//   userInput = document.getElementById("startDate").value;
//   const userInput1 = document.getElementById("endDate").value;
//   url = `"http://localhost:8080/getAsteroidsWithinDateRange?startDate=${userInput}&endDate=${userInput1}"`;
// } else if ((userOption = "getAsteroidsLargerThan")) {
//   userInput = document.getElementById("size").value;
//   url = `"http://localhost:8080/getAsteroidsLargerThan?size=${userInput}"`;
// } else if ((userOption = "getPotentiallyHazardousAsteroids")) {
//   url = "http://localhost:8080/getPotentiallyHazardousAsteroids";
// } else if ((userOption = "getClosestAsteroidsByDistance")) {
//   userInput = document.getElementById("topN").value;
//   url = `"http://localhost:8080/getClosestAsteroidsByDistance?topN=${topN}"`;
// } else if ((userOption = "getFastestAsteroids")) {
//   userInput = document.getElementById("topN").value;
//   url = `"http://localhost:8080/getFastestAsteroids?topN=${topN}"`;
// } else if ((userOption = "getLargestAsteroids")) {
//   userInput = document.getElementById("topN").value;
//   url = `"http://localhost:8080/getLargestAsteroids?topN=${topN}"`;
// }

// fetch(`${url}`, requestOptions)
//   .then((data) => {
//     const nearEarthObjects = Object.values(data.nearEarthObjects).flat();

//     const table = document.querySelector("table");
//     const tableHead = document.querySelector("thead");
//     const tableBody = document.querySelector("tbody");

//     tableHead.innerHTML = "";
//     tableBody.innerHTML = "";

//     const tableData = document.createElement("td");
//     const tableHeadRow = document.getElementById("mainTableHeadRow");

//     nearEarthObjects.forEach((asteroid) => {
//       const closeApproach = asteroid.closeApproachData[0]; //the array has one list

//       const tableRow = document.createElement("tr");

//       if (
//         userOption == "getAsteroidsLargerThan" ||
//         userOption == "getLargestAsteroids"
//       ) {
//         tableData.innerHTML = "Size (km)";
//         tableData.style.backgroundColor = "orange";
//         tableHeadRow.append(tableData);
//         const rowData = [
//           asteroid.id,
//           `<a
//         href="https://ssd.jpl.nasa.gov/tools/sbdb_lookup.html#/?sstr=${asteroid.id}&view=VOP"
//         target="_blank"
//       >
//         ${asteroid.name}
//       </a>`,
//           closeApproach.closeApproachDate,
//           closeApproach.missDistance.kilometers,
//           closeApproach.missDistance.lunar,
//           closeApproach.missDistance.miles,
//           closeApproach.relativeVelocity.kilometersPerHour,
//           asteroid.estimated_diameter.kilometers.estimated_diameter_max
//         ];
//         const sizeData = rowData[6]; //style the size
//         sizeData.style.backgroundColor = "orange";

//         rowData.forEach((value) => {
//           tableData.innerHTML = value;
//           tableRow.appendChild(tableData);
//         });

//         displayNoOfRows();

//         table.appendChild(tableRow);
//       }
//     });
//   })
//   .catch((error) => console.error(error));

function applyFilter() {
  const userOption = document.getElementById("filterSelect").value;
  const token = localStorage.getItem("token");

  const myHeaders = new Headers();
  myHeaders.append("Authorization", `Bearer ${token}`);

  const requestOptions = {
    method: "GET",
    headers: myHeaders,
  };

  let url = "";
  let userInput = "";

  if (userOption === "AsteroidByID") {
    userInput = document.getElementById("asteroidId").value;
    url = `http://localhost:8080/Asteroids/getAsteroidByID?id=${userInput}`;
  } else if (userOption === "AsteroidsByTodayDate") {
    url = "http://localhost:8080/Asteroids/getAsteroidsByTodayDate";
  } else if (userOption === "getAsteroidsWithinDateRange") {
    userInput = document.getElementById("startDate").value;
    const userInput1 = document.getElementById("endDate").value;
    url = `http://localhost:8080/Asteroids/getAsteroidsWithinDateRange?startDate=${userInput}&endDate=${userInput1}`;
  } else if (userOption === "getAsteroidsLargerThan") {
    userInput = document.getElementById("size").value;
    url = `http://localhost:8080/Asteroids/getAsteroidsLargerThan?size=${userInput}`;
  } else if (userOption === "getPotentiallyHazardousAsteroids") {
    url = "http://localhost:8080/Asteroids/getPotentiallyHazardousAsteroids";
  } else if (userOption === "getClosestAsteroidsByDistance") {
    userInput = document.getElementById("topN").value;
    url = `http://localhost:8080/Asteroids/getClosestAsteroidsByDistance?topN=${userInput}`;
  } else if (userOption === "getFastestAsteroids") {
    userInput = document.getElementById("topN").value;
    url = `http://localhost:8080/Asteroids/getFastestAsteroids?topN=${userInput}`;
  } else if (userOption === "getLargestAsteroids") {
    userInput = document.getElementById("topN").value;
    url = `http://localhost:8080/Asteroids/getLargestAsteroids?topN=${userInput}`;
  }

  //   fetch(url, requestOptions)
  //     .then((response) => response.json())
  //     .then((data) => {
  //       //console.log(data.json());
  //       const nearEarthObjects = data;
  //       console.log(nearEarthObjects);

  //       const table = document.querySelector("table");
  //       const tableHead = document.querySelector("thead");
  //       const tableBody = document.querySelector("tbody");

  //       tableHead.innerHTML = "";
  //       tableBody.innerHTML = "";

  //       // Display "No results found" if no data
  //       if (nearEarthObjects.length === 0) {
  //         tableBody.innerHTML =
  //           "<tr><td colspan='7' style='text-align: center; color: red;'>No results found</td></tr>";
  //         table.append(tableBody);
  //         return;
  //       }

  //       const tableHeadRow = document.getElementById("mainTableHeadRow");
  //       tableHeadRow.innerHTML = `
  //       <th rowspan="2">ID</th>
  //       <th rowspan="2">Asteroid Name</th>
  //       <th rowspan="2">Close Approach Date</th>
  //       <th colspan="3">Close Approach Distance</th>
  //       <th rowspan="2">Approach Speed (km/h)</th>
  //     `;

  //       let highlightIndex = null; // Track the column index to highlight
  //       let extraColumnAdded = false;

  //       if (
  //         userOption === "getAsteroidsLargerThan" ||
  //         userOption === "getLargestAsteroids"
  //       ) {
  //         tableHeadRow.innerHTML += `<th style="background-color: orange;">Size (km)</th>`;
  //         highlightIndex = 7; // Highlight the last column
  //         extraColumnAdded = true;
  //       } else if (userOption === "getPotentiallyHazardousAsteroids") {
  //         tableHeadRow.innerHTML += `<th style="background-color: orange;">Hazardous</th>`;
  //         highlightIndex = 7; // Highlight hazardous column
  //         extraColumnAdded = true;
  //       }

  //       tableHead.appendChild(tableHeadRow);

  //       // Populate Table Body
  //       nearEarthObjects.forEach((asteroid) => {
  //         const closeApproach = asteroid.closeApproachData?.[0];
  //         const isHazardous = asteroid.potentiallyHazardousAsteroid;
  //         const size = asteroid.estimatedDiameter.kilometers.estimatedDiameterMax;

  //         const tableRow = document.createElement("tr");

  //         let rowData = [
  //           asteroid.id,
  //           `<a href="https://ssd.jpl.nasa.gov/tools/sbdb_lookup.html#/?sstr=${asteroid.id}&view=VOP" target="_blank">${asteroid.name}</a>`,
  //           closeApproach.close_approach_date,
  //           closeApproach.miss_distance?.kilometers,
  //           closeApproach.miss_distance?.lunar,
  //           closeApproach.miss_distance?.miles,
  //           closeApproach.relative_velocity?.kilometers_per_hour,
  //         ];

  //         if (extraColumnAdded) {
  //           if (
  //             userOption === "getAsteroidsLargerThan" ||
  //             userOption === "getLargestAsteroids"
  //           ) {
  //             rowData.push(size.toFixed(2) || "N/A");
  //           } else if (userOption === "getPotentiallyHazardousAsteroids") {
  //             rowData.push(isHazardous);
  //           }
  //         }

  //         rowData.forEach((value, index) => {
  //           const cell = document.createElement("td");
  //           cell.innerHTML = value;

  //           // Apply highlighting
  //           if (
  //             (userOption === "getClosestAsteroidsByDistance" && index === 3) || // Kilometers column
  //             (userOption === "getFastestAsteroids" && index === 6) || // Approach speed column
  //             (highlightIndex !== null && index === highlightIndex)
  //           ) {
  //             // Dynamic highlight
  //             cell.style.backgroundColor = "orange";
  //           }

  //           tableRow.appendChild(cell);
  //         });

  //         tableBody.appendChild(tableRow);

  //         table.append(tableBody);
  //       });
  //     })
  //     .catch((error) => console.error("Error fetching data:", error));
  // }
  const DEFAULT_TIMEOUT = 30000;

  function createTimer(milliseconds) {
    return new Promise((resolve) => {
      setTimeout(resolve, milliseconds);
    });
  }

  async function fetchDataWithTimeout(url, timeoutMs, requestOptions) {
    const controller = new AbortController();
    const signal = controller.signal;

    const timeoutId = setTimeout(() => {
      controller.abort();
    }, timeoutMs);

    try {
      const response = await fetch(url, { ...requestOptions, signal });
      createTimer(10000);
      //clearTimeout(timeoutId);

      if (!response.ok) {
        throw new Error("HTTP error! Status: ${response.status}");
      }

      return await response.json();
    } catch (error) {
      console.error("Fetch error:", error);
      clearTimeout(timeoutId);
      throw error;
    }
  }

  function fetchDataWithDefaultTimeout(url, requestOptions) {
    return fetchDataWithTimeout(url, DEFAULT_TIMEOUT, requestOptions);
  }

  fetchDataWithDefaultTimeout(url, requestOptions)
    .then((data) => {
      let nearEarthObjects = data; // Assuming it's an array of objects

      if (!nearEarthObjects.length) {
        console.error("No asteroids found.");
        return;
      }

      // Keep only necessary details
      nearEarthObjects = nearEarthObjects.map((asteroid) => ({
        id: asteroid.id,
        name: asteroid.name,
        closeApproachDate: asteroid.closeApproachData.closeApproachDate,
        missDistanceKm: asteroid.closeApproachData.missDistance.kilometers,
        speedKmh: asteroid.closeApproachData.relativeVelocity.kilometersPerHour,
        hazardous: asteroid.potentiallyHazardousAsteroid
      }));

      // Display filtered data
      const tableBody = document.querySelector("tbody");
      tableBody.innerHTML = nearEarthObjects
        .map(
          (asteroid) => `
        <tr>
          <td>${asteroid.id}</td>
          <td><a href="https://ssd.jpl.nasa.gov/tools/sbdb_lookup.html#/?sstr=${asteroid.id}&view=VOP" target="_blank">${asteroid.name}</a></td>
          <td>${asteroid.closeApproachDate}</td>
          <td>${asteroid.missDistanceKm} km</td>
          <td>${asteroid.speedKmh} km/h</td>
          <td>${asteroid.hazardous}</td>
        </tr>
      `
        )
        .join("");

      // Handle empty results
      if (!nearEarthObjects.length) {
        tableBody.innerHTML =
          "<tr><td colspan='6' style='text-align: center; color: red;'>No asteroids found</td></tr>";
      }
    })
    .catch((error) => console.error("Error fetching data:", error));
}
