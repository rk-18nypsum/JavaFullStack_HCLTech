import { useState } from "react";
import heroImg from "./assets/hero.png";
import reactLogo from "./assets/react.svg";
import viteLogo from "./assets/vite.svg";
import "./App.css";
import SampleCode from "./components/SampleCode";
import UseFetchApi from "./components/UseFetchApi";

function App() {
  const employee = [
    {
      ename: "Rahul",
      empid: "32456",
    },
    {
      ename: "Rajeev",
      empid: "7639",
    },
    {
      ename: "Nidhi",
      empid: "23789",
    },
    {
      ename: "Pannu",
      empid: "8753",
    },
  ];

  return (
    <div>
      {/* <h2>Welcomme to the HRMS</h2> */}
      {/* <SampleCode ename=" Rajeev" empid="3127"></SampleCode> */}

      {/* <SampleCode /> */}
      <UseFetchApi />
    </div>
  );
}

export default App;
