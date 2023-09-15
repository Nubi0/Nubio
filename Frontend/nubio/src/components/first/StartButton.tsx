import { StartButtonWrapper } from "../../styles/SHomePage";
import { useNavigate } from "react-router-dom";

const StartButton = () => {
  const navigate = useNavigate();
  return (
    <StartButtonWrapper onClick={() => navigate("/login")}>
      <button>시작하기</button>
    </StartButtonWrapper>
  );
};

export default StartButton;
