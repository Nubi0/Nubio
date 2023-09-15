import { useNavigate } from "react-router-dom";
import { EnjoyButtonWrapper } from "../../styles/SHomePage";

const EnjoyButton = () => {
  const navigate = useNavigate();
  return (
    <EnjoyButtonWrapper>
      <img src={process.env.PUBLIC_URL + '/assets/enjoy.png'} alt="" />
      <button
        onClick={() => {
          navigate("/enjoy");
        }}
      >
        흥미 모드
      </button>
    </EnjoyButtonWrapper>
  );
};

export default EnjoyButton;
