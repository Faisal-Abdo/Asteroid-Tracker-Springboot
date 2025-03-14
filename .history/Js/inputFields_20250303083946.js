function updateFields() {
  const selection = document.getElementById("filterSelect").value;
  const dynamicFields = document.getElementById("dynamicFields");
  dynamicFields.innerHTML = "";

  if (selection === "AsteroidByID") {
    dynamicFields.innerHTML =
      '<p style="color:white;">Enter Asteroid ID:</p><input type="text" id="asteroidId">';
  } else if (selection === "getAsteroidsWithinDateRange") {
    dynamicFields.innerHTML =
      '<div class="datesContainer"> <p style="color:white;">Enter Start Date:</p><input type="date" id="startDate">' +
      '<p style="color:white;">Enter End Date:</p><input type="date" id="endDate"> </div>';
  } else if (selection === "getAsteroidsLargerThan") {
    dynamicFields.innerHTML =
      '<p style="color:white;">Enter size in decimal format:</p><input type="number" step="0.01" id="size">';
  } else if (
    [
      "getClosestAsteroidsByDistance",
      "getFastestAsteroids",
      "getLargestAsteroids",
    ].includes(selection)
  ) {
    dynamicFields.innerHTML =
      '<p style="color:white;">Choose top:</p><select id="topN">' +
      '<option value="1">1</option>' +
      '<option value="5">5</option>' +
      '<option value="10">10</option>' +
      '<option value="20">20</option>' +
      "</select>";
  }
}

function applyFilter() {
  alert("Filter applied!");
}
