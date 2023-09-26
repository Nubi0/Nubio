import { EatWrapper, IconWrapper } from "../../../styles/SSignUpPage";

type EatImages = { [key: string]: string };
type EatListProps = {
  handleImageClick: (name: string, type: string) => void;
  selectedImages: string[];
};

const eatImages: EatImages = {
  한식: process.env.PUBLIC_URL + "/assets/eat/kFood.png",
  양식: process.env.PUBLIC_URL + "/assets/eat/wFood.png",
  일식: process.env.PUBLIC_URL + "/assets/eat/jFood.png",
  중식: process.env.PUBLIC_URL + "/assets/eat/cFood.png",
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
          <>
            <img
              key={name}
              src={src}
              alt={name}
              id={selectedImages.includes(name) ? "check" : "unCheck"}
              onClick={() => handleImageClick(name, "먹기")}
            />
            {/* <h2>{name}</h2> */}
          </>
        ))}
      </IconWrapper>
    </EatWrapper>
  );
};

export default EatList;
