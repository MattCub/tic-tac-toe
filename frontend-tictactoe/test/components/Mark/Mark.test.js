
import { render, screen } from '@testing-library/react';
import Mark from '../../../src/components/Mark/Mark';

jest.mock('../../../src/assets/Circle', () => () => <div data-testid="circle" />);
jest.mock('../../../src/assets/Cross', () => () => <div data-testid="cross" />);

describe('Mark', () => {
	it('renders Circle if symbol is "O" (uppercase)', () => {
		render(<Mark symbol="O" />);
		expect(screen.getByTestId('circle')).toBeInTheDocument();
		expect(screen.queryByTestId('cross')).toBeNull();
	});

	it('renders Circle if symbol is "o" (lowercase)', () => {
		render(<Mark symbol="o" />);
		expect(screen.getByTestId('circle')).toBeInTheDocument();
		expect(screen.queryByTestId('cross')).toBeNull();
	});

		it('renders Cross if symbol is "X"', () => {
			render(<Mark symbol="X" />);
			expect(screen.getByTestId('cross')).toBeInTheDocument();
			expect(screen.queryByTestId('circle')).toBeNull();
		});

		it('renders nothing if symbol is any other value', () => {
			render(<Mark symbol="z" />);
			expect(screen.queryByTestId('cross')).toBeNull();
			expect(screen.queryByTestId('circle')).toBeNull();
		});

		it('renders nothing if symbol is undefined', () => {
			render(<Mark />);
			expect(screen.queryByTestId('cross')).toBeNull();
			expect(screen.queryByTestId('circle')).toBeNull();
		});
});
