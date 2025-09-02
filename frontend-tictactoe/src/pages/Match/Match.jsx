import { useParams } from "react-router-dom";
import styles from "./Match.module.css";

const Match = () => {
  const { id } = useParams();
  
  return (
    <div>
      <h1>MATCH {id}</h1>
    </div>
  );
};

export default Match;
