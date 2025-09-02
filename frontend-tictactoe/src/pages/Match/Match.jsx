import { useParams } from "react-router-dom";

const Match = () => {
  const { id } = useParams();
  
  return (
    <div>
      <h1>MATCH {id}</h1>
    </div>
  );
};

export default Match;
