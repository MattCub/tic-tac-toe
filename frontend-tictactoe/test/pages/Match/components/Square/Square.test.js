import { render, screen, fireEvent } from '@testing-library/react';
import Square from '../../../../../src/pages/Match/components/Square/Square';

jest.mock('../../../../../src/components/Mark/Mark', () => ({ symbol }) => <div data-testid="mark">{symbol}</div>);

describe('Square', () => {
  it('renders the Mark with the correct symbol', () => {
    render(<Square symbol="X" onClick={() => {}} />);
    expect(screen.getByTestId('mark')).toHaveTextContent('X');
  });
});
