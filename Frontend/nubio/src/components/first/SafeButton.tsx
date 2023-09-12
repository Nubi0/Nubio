import { useNavigate } from "react-router-dom";
import { SafeButtonWrapper } from "../../styles/SHomePage";

const SafeButton = () => {
  const navigate = useNavigate();
  return (
    <SafeButtonWrapper>
      <button
        onClick={() => {
          navigate("/safe");
        }}
      >
        안전
      </button>
    </SafeButtonWrapper>
  );
};

export default SafeButton;
