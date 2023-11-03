
const ReportItem = ({ value }: { value: ReportItem }) => {

    return (
        <div>
            <p>{value.title}</p>
            <p>{value.region}</p>
            <p>{value.content}</p>
        </div>
    );
        
  };

  export default ReportItem