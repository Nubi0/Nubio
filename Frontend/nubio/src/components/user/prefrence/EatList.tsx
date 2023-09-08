import { EatWrapper, IconWrapper } from "../../../styles/SSignUpPage";

type EatImages = { [key: string]: string };
type EatListProps = {
  handleImageClick: (name: string) => void;
  selectedImages: string[];
};

const eatImages: EatImages = {
  kFood: process.env.PUBLIC_URL + "/assets/eat/kFood.png",
  wFood: process.env.PUBLIC_URL + "/assets/eat/wFood.png",
  jFood: process.env.PUBLIC_URL + "/assets/eat/jFood.png",
  cFood: process.env.PUBLIC_URL + "/assets/eat/cFood.png",
};

const EatList: React.FC<EatListProps> = ({
  handleImageClick,
  selectedImages,
}) => {
  return (
    <EatWrapper>
      <h1>먹거리</h1>
      <IconWrapper>
        {Object.entries(eatImages).map(([name, src]) => (
          <img
            key={name}
            src={src}
            alt={name}
            id={selectedImages.includes(name) ? "check" : "unCheck"}
            onClick={() => handleImageClick(name)}
          />
        ))}
      </IconWrapper>
    </EatWrapper>
  );
};

export default EatList;