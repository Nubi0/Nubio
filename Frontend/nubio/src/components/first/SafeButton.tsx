import { useNavigate } from "react-router-dom";
import { SafeButtonWrapper } from "../../styles/SHomePage";

const SafeButton = () => {
  const navigate = useNavigate();
  return (
    <SafeButtonWrapper  
      onClick={() => {
      navigate("/safe");
      }}
    >
      <img src={process.env.PUBLIC_URL + '/assets/safe.png'} alt="" />
      <button>
        안전 모드
      </button>
    </SafeButtonWrapper>
  );
};

export default SafeButton;
