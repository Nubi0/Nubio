import { IconWrapper, PlayWrapper } from "../../../styles/SSignUpPage";

type PlayImages = { [key: string]: string };
type PlayListProps = {
  handleImageClick: (name: string, type: string) => void;
  selectedImages: string[];
};

const playImages: PlayImages = {
  테마파크: process.env.PUBLIC_URL + "/assets/user/play/amusementPark.png",
  공방: process.env.PUBLIC_URL + "/assets/user/play/craftShop.png",
  문화재: process.env.PUBLIC_URL + "/assets/user/play/culturalHeritage.png",
  보드게임: process.env.PUBLIC_URL + "/assets/user/play/dice.png",
  노래방: process.env.PUBLIC_URL + "/assets/user/play/karaoke.png",
  영화: process.env.PUBLIC_URL + "/assets/user/play/movie.png",
  해수욕장: process.env.PUBLIC_URL + "/assets/user/play/sea.png",
};

const PlayList: React.FC<PlayListProps> = ({
  handleImageClick,
  selectedImages,
}) => {
  // 이미지

  return (
    <PlayWrapper>
      <h1>놀거리</h1>
      <IconWrapper>
        {Object.entries(playImages).map(([name, src]) => (
          <img
            key={name}
            src={src}
            alt={name}
            id={selectedImages.includes(name) ? "check" : "unCheck"}
            onClick={() => handleImageClick(name, "놀기")}
          />
        ))}
      </IconWrapper>
    </PlayWrapper>
  );
};

export default PlayList;
