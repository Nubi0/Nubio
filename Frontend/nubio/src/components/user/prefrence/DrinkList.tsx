import { DrinkWrapper, IconWrapper } from "@styles/SSignUpPage";

type DrinkImages = { [key: string]: string };
type DrinkListProps = {
  handleImageClick: (name: string, type: string) => void;
  selectedImages: string[];
};
const drinkImages: DrinkImages = {
  맥주: process.env.PUBLIC_URL + "/assets/user/drink/beer.png",
  칵테일: process.env.PUBLIC_URL + "/assets/user/drink/cocktail.png",
  커피: process.env.PUBLIC_URL + "/assets/user/drink/coffee.png",
  아이스크림: process.env.PUBLIC_URL + "/assets/user/drink/iceCream.png",
  막걸리: process.env.PUBLIC_URL + "/assets/user/drink/riceWine.png",
  소주: process.env.PUBLIC_URL + "/assets/user/drink/soju.png",
  와인: process.env.PUBLIC_URL + "/assets/user/drink/wine.png",
};

const DrinkList: React.FC<DrinkListProps> = ({
  handleImageClick,
  selectedImages,
}) => {
  return (
    <DrinkWrapper>
      <h1>마실거리</h1>
      <IconWrapper>
        {Object.entries(drinkImages).map(([name, src]) => (
          <img
            key={name}
            src={src}
            alt={name}
            id={selectedImages.includes(name) ? "check" : "unCheck"}
            onClick={() => handleImageClick(name, "마시기")}
          />
        ))}
      </IconWrapper>
    </DrinkWrapper>
  );
};

export default DrinkList;
