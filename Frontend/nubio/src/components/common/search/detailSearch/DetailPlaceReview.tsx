import { useState } from "react";
import {
  PlaceReviewWrapper,
  ReviewForm,
} from "../../../../styles/SPlaceDetailPage";
import Box from "@mui/material/Box";
import Rating from "@mui/material/Rating";
import Stars from "../searchList/Stars";
import {
  DetailPlaceReviewWrapper,
  DetailReviewCount,
  DetailReviewForm,
  DetailScore,
} from "../../../../styles/SSearchBar";

const DetailPlaceReview = ({ review }: { review: reviewProps }) => {
  // const star = process.env.PUBLIC_URL + '/assets/star.svg';
  const [value, setValue] = useState<number | null>(0);
  const score = review.score.toFixed(1);

  console.log(review);
  return (
    <PlaceReviewWrapper>
      <DetailPlaceReviewWrapper>
        <DetailReviewCount>리뷰 {review.count}개</DetailReviewCount>
        <Stars score={review.score} />
        <DetailScore>{score}점</DetailScore>
      </DetailPlaceReviewWrapper>
      <DetailReviewForm>
        <input type="text" placeholder="이 장소의 후기를 남겨주세요." />
        <Box>
          <Rating
            value={value}
            onChange={(event, newValue) => {
              setValue(newValue);
            }}
          />
        </Box>
      </DetailReviewForm>
    </PlaceReviewWrapper>
  );
};

export default DetailPlaceReview;
