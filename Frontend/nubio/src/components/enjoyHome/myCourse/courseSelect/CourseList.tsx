import { DragDropContext, Droppable, Draggable } from "react-beautiful-dnd";
import { PinWrapper, CoursePinWrapper } from "@styles/SCourseSelectPage";
import CoursePin from "./CoursePin";

const CourseList = ({ positions }: { positions: placeItem[] }) => {
  const handleChange = () => {
    console.log("드래그");
  };
  return (
    // 드래그 앤 드랍을 만들었지만 기능 상 아무런 기능을 하지 않는 것 같아 나중에 고치기
    <DragDropContext onDragEnd={handleChange}>
      <PinWrapper>
        <Droppable droppableId="box" direction="horizontal">
          {(provided) => (
            <div
              ref={provided.innerRef}
              {...provided.droppableProps}
              style={{ display: "flex", overflowX: "auto" }}
            >
              {positions.map((value: placeItem, index: number) => {
                return (
                  <Draggable
                    draggableId={index.toString()}
                    index={index}
                    key={index}
                  >
                    {(provided, snapshot) => (
                      <CoursePinWrapper
                        ref={provided.innerRef}
                        {...provided.draggableProps}
                        {...provided.dragHandleProps}
                        key={index}
                      >
                        <CoursePin key={index} value={value} />
                      </CoursePinWrapper>
                    )}
                  </Draggable>
                );
              })}
              {provided.placeholder}
            </div>
          )}
        </Droppable>
      </PinWrapper>
    </DragDropContext>
  );
};

export default CourseList;
