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

  const DEFAULT_TIMEOUT = 30000;

  function createTimer(milliseconds) {
    return new Promise((resolve) => {
      setTimeout(resolve, milliseconds);
    });
  }
  const iframe = document.getElementById("externalPage");

  async function fetchDataWithTimeout(url, timeoutMs, requestOptions) {
    const controller = new AbortController();
    const signal = controller.signal;

    const timeoutId = setTimeout(() => {
      controller.abort();
    }, timeoutMs);

    iframe.onload = () => {
      const iframeDocument = iframe.contentDocument || iframe.contentWindow.document;

      iframeDocument.document.getElementById("loading-overlay").classList.remove("hideLoader"); // Show loading

    }
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
    // finally {
    //   iframeDocument.document.getElementById("loading-overlay").classList.add("hideLoader"); // hide loading

    // }
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

        return mappedAsteroid;
      });

      // Display filtered data
      const table = document.querySelector("tbody");
      const tableHeader = document.getElementById("mainTableHeadRow");

      // Clear all rows before populating new data
      while (table.firstChild) {
        table.removeChild(table.firstChild);
      }

      // Conditionally add "isHazardous" header if the filter is selected
      if (userOption === "getPotentiallyHazardousAsteroids") {
        // Check if header already exists to avoid duplicate addition.
        if (!document.getElementById("hazardousHeader")) {
          const th = document.createElement("th");
          th.id = "hazardousHeader";
          th.innerText = "is Hazardous";
          th.rowSpan = "2";
          th.style.backgroundColor = "#474d4c"; // highlight header
          tableHeader.appendChild(th);
        }
      } else {
        // Remove "isHazardous" header if the filter is not selected
        const hazardousHeader = document.getElementById("hazardousHeader");
        if (hazardousHeader) {
          tableHeader.removeChild(hazardousHeader);
        }
      }

      // Conditionally add "Size" header if the filter is selected
      if (
        userOption === "getAsteroidsLargerThan" ||
        userOption === "getLargestAsteroids"
      ) {
        // Check if header already exists to avoid duplicate addition.
        if (!document.getElementById("sizeHeader")) {
          const th = document.createElement("th");
          th.id = "sizeHeader";
          th.innerText = "Size (km)";
          th.rowSpan = "2";
          tableHeader.appendChild(th);
        }
      } else {
        // Remove "Size" header if the filter is not selected
        const sizeHeader = document.getElementById("sizeHeader");
        if (sizeHeader) {
          tableHeader.removeChild(sizeHeader);
        }
      }

      // Insert rows for each asteroid
      nearEarthObjects.forEach((asteroid) => {
        const row = table.insertRow(); // Create a new row for each asteroid

        // Build row HTML
        let rowHTML = `
        <td>${asteroid.id}</td>
        <td><a href="https://ssd.jpl.nasa.gov/tools/sbdb_lookup.html#/?sstr=${asteroid.id}&view=VOP" target="_blank">${asteroid.name}</a></td>
        <td>${asteroid.closeApproachDate}</td>
        <td>${asteroid.missDistanceKm}</td>
        <td>${asteroid.lunarDistance}</td>
        <td>${asteroid.missDistanceMile}</td>
        <td>${asteroid.speedKmh}</td>
      `;

        // Conditionally add "isHazardous" column if the filter is selected
        if (userOption === "getPotentiallyHazardousAsteroids") {
          rowHTML += `<td style="background-color: #590e0e;">${
            asteroid.isHazardous ? "Yes" : "No"
          }</td>`;
        }

        // Conditionally add "Size" column if the filter is selected
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