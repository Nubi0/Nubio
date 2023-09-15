import { useNavigate } from "react-router-dom";
import { SafeButtonWrapper } from "../../styles/SHomePage";

const SafeButton = () => {
  const navigate = useNavigate();
  return (
    <SafeButtonWrapper>
      <img src={process.env.PUBLIC_URL + '/assets/safe.png'} alt="" />
      <button
        onClick={() => {
          navigate("/safe");
        }}
      >
        안전 모드
      </button>
    </SafeButtonWrapper>
  );
};

export default SafeButton;
