const url = "http://localhost:8080/film";

export const apiGetAllFilms = async () => {
    let data = [];

    await fetch(`${url}`, {
      method: "GET",
      headers: {
        "Content-type": "application/json",
      }
    })
      .then(async (response) => {
        data = await response.json();
      })
      .catch(async (error) => {
        console.log(error)
      });
    return data;
  };