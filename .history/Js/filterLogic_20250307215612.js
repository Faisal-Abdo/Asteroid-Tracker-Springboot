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

  // ... existing code to build URL and call fetchDataWithDefaultTimeout remains unchanged

  fetchDataWithDefaultTimeout(url, requestOptions)
    .then((data) => {
      let nearEarthObjects = data; // Assuming it's an array of objects

      if (!nearEarthObjects.length) {
        console.error("No asteroids found.");
        return;
      }

      // Keep only necessary details and include extra data if available.
      nearEarthObjects = nearEarthObjects.map((asteroid) => {
        // Base object mapping:
        const mappedAsteroid = {
          id: asteroid.id,
          name: asteroid.name,
          closeApproachDate: asteroid.closeApproachData[0].closeApproachDate,
          missDistanceKm: asteroid.closeApproachData[0].missDistance.kilometers,
          lunarDistance: asteroid.closeApproachData[0].missDistance.lunar,
          missDistanceMile: asteroid.closeApproachData[0].missDistance.miles,
          speedKmh:
            asteroid.closeApproachData[0].relativeVelocity.kilometersPerHour,
          size: asteroid.estimatedDiameter.kilometers.estimatedDiameterMax,
          isHazardous: asteroid.potentiallyHazardousAsteroid,
        };

        // For larger asteroids, assume the API returns a "size" property.
        // if (
        //   userOption === "getAsteroidsLargerThan" ||
        //   userOption === "getLargestAsteroids"
        // ) {
        //   mappedAsteroid.size = asteroid.size; // adjust key as per your API response
        // }

        // // For potentially hazardous asteroids, assume the API returns a "isHazardous" property.
        // if (userOption === "getPotentiallyHazardousAsteroids") {
        //   mappedAsteroid.isHazardous = asteroid.isHazardous; // adjust key as per your API response
        // }

        return mappedAsteroid;
      });

      // Display filtered data
      const table = document.querySelector("tbody");
      const tableHeader = document.getElementById("mainTableHeadRow");

      // Clear all rows before populating new data
      while (table.firstChild) {
        table.removeChild(table.firstChild);
      }
      document.getElementById("sizeHeader")?.remove();
      document.getElementById("hazardousHeader")?.remove();

      if (userOption === "getPotentiallyHazardousAsteroids") {
        // Check if header already exists to avoid duplicate addition.
        const th = document.createElement("th");
        th.id = "hazardousHeader";
        th.innerText = "isHazardous";
        th.style.backgroundColor = "#474d4c"; // highlight header
        th.rowSpan = "2";
        tableHeader.appendChild(th);
      }

      // For larger/largest asteroids, add a Size column header if not already added.
      if (
        userOption === "getAsteroidsLargerThan" ||
        userOption === "getLargestAsteroids"
      ) {
        const th = document.createElement("th");
        th.id = "sizeHeader";
        th.innerText = "Size";
        th.rowSpan = "2";
        tableHeader.appendChild(th);
      }

      // Insert rows for each asteroid
      nearEarthObjects.forEach((asteroid) => {
        const row = table.insertRow(); // Create a new row for each asteroid
        // Build inner HTML string (using template literals for clarity).
        let rowHTML = `
      <td${idCellStyle}>${asteroid.id}</td>
      <td><a href="https://ssd.jpl.nasa.gov/tools/sbdb_lookup.html#/?sstr=${asteroid.id}&view=VOP" target="_blank">${asteroid.name}</a></td>
      <td>${asteroid.closeApproachDate}</td>
      <td${kmCellStyle}>${asteroid.missDistanceKm}</td>
      <td>${asteroid.lunarDistance}</td>
      <td>${asteroid.missDistanceMile}</td>
      <td${speedCellStyle}>${asteroid.speedKmh}</td>
    `;

        // For potentially hazardous asteroids, add the hazardous value in a new cell.
        if (userOption === "getPotentiallyHazardousAsteroids") {
          rowHTML += `<td style="background-color: #590e0e;">${
            asteroid.isHazardous ? "Yes" : "No"
          }</td>`;
        }

        // For larger asteroids, add the size data in a new cell.
        if (
          userOption === "getAsteroidsLargerThan" ||
          userOption === "getLargestAsteroids"
        ) {
          rowHTML += `<td>${asteroid.size}</td>`;
        }

        row.innerHTML = rowHTML;
      });
      displayNoOfRows();
    })
    .catch((error) => console.error("Error fetching data:", error));
}

//   fetchDataWithDefaultTimeout(url, requestOptions)
//     .then((data) => {
//       let nearEarthObjects = data; // Assuming it's an array of objects

//       if (!nearEarthObjects.length) {
//         console.error("No asteroids found.");
//         return;
//       }

//       // Keep only necessary details
//       nearEarthObjects = nearEarthObjects.map((asteroid) => ({
//         id: asteroid.id,
//         name: asteroid.name,
//         closeApproachDate: asteroid.closeApproachData[0].closeApproachDate,
//         missDistanceKm: asteroid.closeApproachData[0].missDistance.kilometers,
//         lunarDistance: asteroid.closeApproachData[0].missDistance.lunar,
//         missDistanceMile: asteroid.closeApproachData[0].missDistance.miles,
//         speedKmh:
//           asteroid.closeApproachData[0].relativeVelocity.kilometersPerHour,
//       }));

//       // Display filtered data
//       const table = document.querySelector("tbody");

//       // Clear all rows before populating new data
//       while (table.firstChild) {
//         table.removeChild(table.firstChild);
//       }

//       // If no near-Earth objects are available, show "No asteroids found"
//       if (!nearEarthObjects.length) {
//         table.innerHTML =
//           "<tr><td colspan='7' style='text-align: center; color: red;'>No asteroids found</td></tr>";
//         return;
//       }

//       // Insert rows for each asteroid
//       nearEarthObjects.forEach((asteroid) => {
//         const row = table.insertRow(); // Create a new row for each asteroid

//         row.innerHTML = `
//         <td>${asteroid.id}</td>
//         <td><a href="https://ssd.jpl.nasa.gov/tools/sbdb_lookup.html#/?sstr=${asteroid.id}&view=VOP" target="_blank">${asteroid.name}</a></td>
//         <td>${asteroid.closeApproachDate}</td>
//         <td>${asteroid.missDistanceKm}</td>
//         <td>${asteroid.lunarDistance}</td>
//         <td>${asteroid.missDistanceMile}</td>
//         <td>${asteroid.speedKmh}</td>
//       `;
//       });
//       displayNoOfRows();
//     })
//     .catch((error) => console.error("Error fetching data:", error));
// }
