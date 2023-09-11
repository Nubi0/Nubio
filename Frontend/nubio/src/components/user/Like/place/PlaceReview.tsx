import { PlaceReviewWrapper, Score, ReviewCount } from '../../../../styles/SLikePage';
import Stars from './Stars';

type reviewProps = {
  score: number;
  count: number;
};

const PlaceReview = ({ review }: { review: reviewProps }) => {
  const score = review.score.toFixed(1);
  return (
    <PlaceReviewWrapper>
      <Score>{score}</Score>
      <Stars score={review.score} />
      <ReviewCount>리뷰 {review.count}</ReviewCount>
    </PlaceReviewWrapper>
  );
};

export default PlaceReview;
