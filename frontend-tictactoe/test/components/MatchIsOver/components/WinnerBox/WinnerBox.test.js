import { render } from "@testing-library/react";
import WinnerBox from "../../../../../src/components/MatchIsOver/components/WinnerBox/WinnerBox";

jest.mock("../../../../../src/components/Mark/Mark", () => ({
  __esModule: true,
  default: ({ symbol }) => <div data-testid="mark">{symbol}</div>,
}));

describe("WinnerBox", () => {
  it("renders the Mark component with the winner symbol", () => {
    const { getByTestId } = render(<WinnerBox winner="O" />);
    expect(getByTestId("mark")).toHaveTextContent("O");
  });
});
