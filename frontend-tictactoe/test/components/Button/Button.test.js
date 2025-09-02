import { render, screen, fireEvent } from '@testing-library/react';
import Button from '../../../src/components/Button/Button';

describe('Button', () => {
  it('renders with the given label', () => {
    render(<Button label="Start game" onClick={() => {}} />);
    expect(screen.getByRole('button', { name: "Start game" })).toBeInTheDocument();
  });

  it('calls onClick when clicked', () => {
    const handleClick = jest.fn();
    render(<Button label="Start game" onClick={handleClick} />);
    fireEvent.click(screen.getByRole('button', { name: "Start game" }));
    expect(handleClick).toHaveBeenCalledTimes(1);
  });
});
