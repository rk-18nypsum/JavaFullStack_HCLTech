import React, { useEffect, useState } from "react";

const UseFetchApi = () => {
  const [serverData, setServerData] = useState([]);
  useEffect(() => {
    async function getData() {
      try {
        const serverData = await fetch("http://localhost:8080/");
        const jsonData = await serverData.text();
        console.log(jsonData);
        setServerData(jsonData);
      } catch (e) {
        console.log(e);
      }
    }
    getData();
  }, []);
  return (
    <div>
      <h2>{JSON.stringify(serverData)}</h2>
    </div>
  );
};

export default UseFetchApi;
