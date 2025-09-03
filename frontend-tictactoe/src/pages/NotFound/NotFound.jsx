import styles from './NotFound.module.css';

const NotFound = () => {
  return (
    <div className={styles.NotFound}>
      <h1>404 - Not Found</h1>
      <p className={styles.NotFound_description}>The page you are looking for does not exist.</p>
    </div>
  );
};

export default NotFound;